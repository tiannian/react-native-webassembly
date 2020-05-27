import { NativeModules } from 'react-native';

class Module {}

class Instance {}

class Memory {}

class Global {}

class Table {}

const WebAssembly = {
    instantiate = NativeModules.WebAssembly.instantiate,
    Module,
    Instance,
    Memory,
    Global,
    Table,
}

export default WebAssembly;
