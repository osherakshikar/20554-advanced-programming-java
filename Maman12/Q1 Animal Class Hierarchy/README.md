# 🦁 Animal Class Hierarchy

## 🌟 Overview
This project demonstrates advanced object-oriented programming concepts in Java through an animal class hierarchy. It implements inheritance, polymorphism, abstract classes, method overriding, and deep cloning with a focus on proper object design.

## 🏗️ Class Structure
This hierarchy follows a logical organization of animal types:

- **`Animal`** (Abstract base class)
  - **`Mammal`** (Abstract subclass)
    - **`Chimpanzee`** (Concrete class)
    - Anonymous `Dog` implementation
  - **`Bird`** (Abstract subclass)
    - **`Parrot`** (Concrete class with `Owner`)
    - **`Owl`** (Concrete class)

Additionally, a supporting `Owner` class demonstrates object composition and cloning.

## 🔑 Key Features

### 📚 Inheritance
- Multi-level inheritance with abstract intermediate classes
- Proper method overriding using `@Override`
- Type-specific implementations of common behaviors

### 🧩 Polymorphism
- Animals stored in a single `ArrayList<Animal>`
- Runtime type identification using `instanceof`
- Dynamic method dispatch for common methods

### 📋 Abstract Methods & Classes
- Abstract `Animal` and intermediate classes (`Mammal`, `Bird`)
- Abstract `eat()` method enforcing implementation in concrete classes
- Type-specific behaviors (e.g., `fly()`, `run()`, `climb()`)

### 🧬 Object Cloning
- Implementation of the `Cloneable` interface
- Deep copying of composite objects (`Parrot` with `Owner`)
- Demonstration of reference independence after cloning

### ⚖️ Object Equality
- Proper `equals()` implementation throughout the hierarchy
- Type-safe equality checking using `getClass()` comparisons

## 📊 Implementation Details

### Animal Class
- Base abstract class with common properties (name, age, color)
- Abstract `eat()` method
- Concrete `sleep()` method
- Base `toString()` implementation
- Implements `Cloneable` for object copying

### Mammal Class
- Adds mammal-specific behavior (`run()`)
- Extends base `toString()` with type information

### Bird Class
- Adds bird-specific behavior (`fly()`)
- Extends base `toString()` with type information

### Concrete Animal Classes
- Specific implementations for different animal types
- Custom behaviors (e.g., `Chimpanzee.climb()`, `Parrot.displayOwnerInfo()`)
- Specialized `eat()` implementations

### Owner Class
- Demonstration of object composition
- Implements `Cloneable` for deep copying
- Custom `equals()` and `toString()` methods

## 🚀 Execution Flow
The `AnimalHierarchy` main class demonstrates:
1. Creating different types of animals
2. Adding them to a common collection
3. Demonstrating polymorphic behavior
4. Showing type-specific method calls
5. Demonstrating deep cloning of complex objects
6. Verifying clone independence

## 🧠 Learning Concepts
This project demonstrates several important Java and OOP concepts:
- 🔄 Inheritance and class hierarchies
- 🎭 Polymorphism and dynamic binding
- 🛑 Abstract classes and methods
- 🔍 Method overriding and `toString()` customization
- 👯 Object cloning (shallow and deep)
- 🧪 Type testing with `instanceof`
- 📋 Collection management of heterogeneous objects
