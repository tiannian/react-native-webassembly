use jni::objects::{JClass, JObject, JValue};
use jni::sys::{jbyteArray, jobject};
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

// thread_local! {
// static WASMS: RefCell<Vec<ModuleRef>> = RefCell::new(Vec::new());
// }

fn instantiate_web_assembly(
    env: &JNIEnv,
    _class: &JClass,
    input: jbyteArray,
) -> Result<jobject, WasmError> {
    // let code = env.convert_byte_array(input)?;
    // let module = wasmi::Module::from_buffer(code)?;
    // let instant = ModuleInstance::new(&module, &ImportsBuilder::default())?.assert_no_start();

    // create CWebAssemblyInstance object.
    let cl = env.find_class("com/reactlibrary/CWebAssemblyInstance")?;
    let mut index: i32 = 0;

    /*     WASMS.with(|f| { */
    // let wasms = f.borrow();
    // index = wasms.len().try_into().unwrap();
    // let mut wasms_mut = f.borrow_mut();
    // wasms_mut.push(instant);
    /* }); */
    let mt = env.get_method_id("com/reactlibrary/CWebAssemblyInstance", "<init>", "(I)V")?;
    let obj = env.new_object_unchecked(cl, mt, &[JValue::Int(index)])?;

    Ok(obj.into_inner())
}

#[no_mangle]
pub extern "system" fn Java_com_reactlibrary_CWebAssembly_instantiate(
    env: JNIEnv,
    class: JClass,
    input: jbyteArray,
) -> jobject {
    match instantiate_web_assembly(&env, &class, input) {
        Ok(v) => v,
        Err(e) => {
            let _ = env.throw_new("java/lang/Exception", format!("{:?}", e));
            let obj = JObject::null();
            obj.into_inner()
        }
    }
}
