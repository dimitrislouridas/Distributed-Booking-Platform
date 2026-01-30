# 🏨 Distributed Booking Platform (MapReduce)

A distributed booking platform developed as part of a university **Distributed Systems** project.  
The system follows a **Master–Worker architecture** and applies **MapReduce-style processing**
to support scalable search and filtering of room listings.

---

## 🔍 Overview
The application allows **hosts** to publish room listings and availability, while **users** can search,
filter, and submit booking requests.  
Client requests are distributed across multiple **worker nodes**, which process data in parallel and
return aggregated results to a central **coordinator (master)**.

The design focuses on **parallelism**, **scalability**, and **fault-aware request handling**.

---

## ✨ Features
- Hosts publish room listings and availability
- Users search and filter listings (area, dates, price, capacity, etc.)
- Booking request workflow with concurrency handling
- Client–server communication via **TCP sockets**
- **Multithreaded backend** for handling multiple simultaneous requests
- Simple Java-based client interface

---

## 🏗 Architecture
The system is organized around a central **Master node** responsible for:
- Distributing tasks to workers
- Aggregating intermediate results
- Returning final responses to clients

Worker nodes execute **Map-style tasks** on subsets of data and return partial results, which are
combined using **Reduce-style aggregation**.

![Distributed Booking Platform Architecture](assets/architecture.png)

---

## ⚙️ How to Run (High-Level)
1. Start the **Master / Coordinator** node
2. Launch one or more **Worker** nodes
3. Run client applications to publish listings, search, or submit bookings

---

## 🛠 Tech Stack
- **Java**
- Gradle
- TCP Sockets
- Multithreading
- Distributed Systems
- MapReduce Concepts

---

## 📚 What I Learned
- Designing distributed workflows using a **Master–Worker architecture**
- Applying **MapReduce principles** to scalable query processing
- Handling **concurrency and parallel execution** in Java
- Building structured, backend-oriented distributed systems
