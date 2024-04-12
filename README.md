# Servidor HTTP en Java con Patrón Singleton

Este proyecto consiste en la implementación de un servidor HTTP en Java utilizando el patrón de diseño Singleton. El servidor es capaz de manejar conexiones TCP, implementar el protocolo HTTP y realizar multithreading para atender múltiples solicitudes simultáneamente.

## Funcionalidades principales:

- **Protocolo HTTP**: El servidor implementa los protocolos HTTP/1.1 según las especificaciones de los documentos RFC 7230, RFC 7231 y RFC 7232.

- **Conexiones TCP**: Utiliza sockets de servidor para establecer conexiones TCP con los clientes.

- **Multithreading**: El servidor es capaz de manejar múltiples solicitudes simultáneamente gracias a la utilización de hilos (threads).

- **Patrón Singleton**: Se utiliza el patrón Singleton para garantizar que solo exista una instancia del servidor en todo el sistema.

## Documentación y Referencias:

Para el desarrollo de este proyecto, se consultaron diversas fuentes de documentación, entre ellas:

- Video tutorial series: [Java HTTP Server Tutorial](https://www.youtube.com/watch?v=ZESFHxvypUg&list=PLAuGQNR28pW56GigraPdiI0oKwcs8gglW&index=8) by [CoderFromScratch](https://www.youtube.com/@coderfromscratch1486)
- [RFC 7230: Hypertext Transfer Protocol (HTTP/1.1): Message Syntax and Routing](https://datatracker.ietf.org/doc/html/rfc7230)
- [RFC 7231: Hypertext Transfer Protocol (HTTP/1.1): Semantics and Content](https://datatracker.ietf.org/doc/html/rfc7231)
- [RFC 7232: Hypertext Transfer Protocol (HTTP/1.1): Conditional Requests](https://datatracker.ietf.org/doc/html/rfc7232)
- Medium article: [Creating a HTTP Server in Java](https://rjlfinn.medium.com/creating-a-http-server-in-java-9b6af7f9b3cd) by [Ryan Finn](https://rjlfinn.medium.com/)

Estas fuentes fueron fundamentales para comprender los detalles del protocolo HTTP, la implementación del servidor y las mejores prácticas en Java para el manejo de conexiones TCP y multithreading.

## Capturas de Pantalla:

![Sin título](https://github.com/Agslz/java-http-server/assets/83142033/353c27ef-5a8f-4d96-b399-6d98782d87cc)
