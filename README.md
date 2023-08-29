# Echo UDP

Simple UDP client-server communication system.

UDPClient:
The UDPClient class represents the client side. When executed, it prompts the user for a message input, sends the message as a UDP packet to a specified server address and port, waits for a response from the server, and then displays the received message.

UDPServer:
The UDPServer class represents the server side. When executed, it listens on a designated port for incoming UDP packets. Upon receiving a packet, it converts the enclosed message to uppercase, sends the modified message back to the client, and effectively mirrors the client's message in uppercase.

In essence, the UDPClient sends a message to the UDPServer, which responds with the same message converted to uppercase. This showcases a basic application of UDP communication between a client and a server. However, keep in mind that UDP lacks guarantees for reliable delivery and order preservation, making it more suitable for scenarios where speed is prioritized over data integrity.

Project made during the Academia de Código bootcamp between May -> Aug 2023. www.academiadecodigo.org
