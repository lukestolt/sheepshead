import { Server } from 'http';

export class ServerConfig {
    static host: string = 'localhost';
    static port: string = '8080'; 
    static serverUrl: string = ServerConfig.host + ':' + ServerConfig.port;
}

