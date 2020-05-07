
export class ServerConfig {
    static host: string = 'sheepsheadbackend-env.eba-dxkajvz3.ca-central-1.elasticbeanstalk.com';
    static port: string = '5000'; 
    static serverUrl: string = 'http://' + ServerConfig.host; //+ ':' + ServerConfig.port;

//     static host: string = '192.168.1.16';
//     static port: string = '8080'; 
//     static serverUrl: string = 'http://' + ServerConfig.host + ':' + ServerConfig.port;
}
