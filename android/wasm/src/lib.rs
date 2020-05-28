use jni::objects::{JClass, JObject, JValue};
use jni::sys::{jbyteArray, jint};
use jni::JNIEnv;
use std::cell::RefCell;
use std::convert::TryInto;
use wasmi::{ImportsBuilder, ModuleInstance, ModuleRef};

#[derive(Debug)]
enum WasmError {
    JNIError(jni::errors::Error),
    WasmiError(wasmi::Error),
}

impl From<jni::errors::Error> for WasmError {
    fn from(e: jni::errors::Error) -> Self {
        WasmError::JNIError(e)
    }
}

impl From<wasmi::Error> for WasmError {
    fn from(e: wasmi::Error) -> Self {
        WasmError::WasmiError(e)
    }
}

thread_local! {
static WASMS: RefCell<Vec<ModuleRef>> = RefCell::new(Vec::new());
}

fn instantiate_web_assembly(
    env: &JNIEnv,
    _class: &JClass,
    input: jbyteArray,
) -> Result<i32, WasmError> {
    let code = env.convert_byte_array(input)?;
    let module = wasmi::Module::from_buffer(code)?;
    let instant = ModuleInstance::new(&module, &ImportsBuilder::default())?.assert_no_start();

    // create CWebAssemblyInstance object.
    let mut index: i32 = 0;

    WASMS.with(|f| {
        let mut wasms = f.borrow_mut();
        index = wasms.len().try_into().unwrap();
        wasms.push(instant);
    });

    Ok(index)
}

#[no_mangle]
pub extern "system" fn Java_com_reactlibrary_CWebAssembly_instantiate(
    env: JNIEnv,
    class: JClass,
    input: jbyteArray,
) -> jint {
    match instantiate_web_assembly(&env, &class, input) {
        Ok(v) => v,
        Err(e) => {
            let _ = env.throw_new("java/lang/Exception", format!("{:?}", e));
            0
        }
    }
}
