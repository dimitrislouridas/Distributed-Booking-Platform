# Distributed Booking Platform (MapReduce)

Distributed booking platform developed as part of a university **Distributed Systems** project.
The system follows a **Master–Worker** architecture and applies **MapReduce-style** processing for search and filtering.

## Features
- Hosts publish room listings and availability
- Users search and filter listings (e.g., area, dates, price, capacity)
- Booking request workflow with concurrency handling
- Client–server communication via TCP sockets
- Multithreaded backend for handling multiple requests

## Tech Stack
- Java
- TCP Sockets
- Multithreading
- Distributed Systems / MapReduce concepts

## Project Structure
- `...` (add 2–3 folders if you want, otherwise leave it out)

## How to Run (high level)
1. Start the master/coordinator
2. Start worker nodes
3. Run client applications to publish/search/book listings

## What I learned
- Designing distributed workflows using a Master–Worker architecture
- Applying MapReduce ideas to scalable query processing
- Writing maintainable Java code with clear responsibilities
