# 📌 Smart Study Planner & Productivity Tracker

## Project Brief
A Java-based application that helps students plan study schedules, manage tasks, and track productivity.  
(Current Phase: Console-based prototype)

---

## 🎯 Problem Statement
Students often struggle with:
- Organizing their study time  
- Staying consistent with schedules  
- Tracking productivity  

---

## 👥 Target Users
- School & college students  
- Exam aspirants  
- Anyone who wants to improve study productivity  

---

## 🛠️ OOP Concepts Used
- **Encapsulation** → private fields with getters/setters (e.g., Task details).  
- **Inheritance** → `StudyTask` extends abstract `Task`.  
- **Polymorphism** → `weight()` method overridden for different task types.  
- **Abstraction** → Abstract class `Task` and interface `TaskRepository`.  
- **Association** → User ↔ Tasks relationship in design.  

---

## 📊 Class Diagram
```mermaid
classDiagram
    class Task { 
      -int id
      -String title
      -LocalDate dueDate
      -Status status
      +markComplete()
      +weight()* int
    }
    class StudyTask { +subject: String }
    Task <|-- StudyTask

    class TaskRepository { 
      +add(Task)
      +findById(int) Task
      +findAll() List~Task~
    }
    class InMemoryTaskRepository
    TaskRepository <|.. InMemoryTaskRepository

    class TaskService { 
      +addStudyTask(String,String,LocalDate) Task
      +complete(int) void
      +list() List~Task~
    }
    TaskService --> TaskRepository

    class ProductivityCalculator { +score(List~Task~, int) int }
