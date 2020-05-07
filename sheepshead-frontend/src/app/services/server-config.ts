
export class ServerConfig {
    static host: string = 'localhost';
    static port: string = '5000'; 
    static serverUrl: string = 'http://' + ServerConfig.host + ':' + ServerConfig.port;
}
