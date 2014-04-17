/**
 * -----------------------
 * Start Static Web Server
 * -----------------------
 */
var connect = require('connect');

console.log("Starting ...");
connect.createServer(connect.static(__dirname)).listen(8888);
console.log("Started static web site on port: " + 8888);
