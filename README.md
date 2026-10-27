# MiniBank (Java OOP Mini Project)

A Java-based banking system demonstrating OOP concepts (inheritance, polymorphism, collections, exceptions, generics, file I/O patterns, etc.) across multiple lab versions.

## Structure
- `model/` - Core entities (Account, SavingsAccount, CurrentAccount, FixedDepositAccount, Customer, etc.)
- `service/` - Services like Repository, Command, CommandParser
- `util/` - Utilities (Validator, StatementFormatter, TransactionLog, StatePersister, ReportGenerator, AnnotationValidator)
- `exception/` - Custom exceptions (BankException, InsufficientFundsException, InvalidAmountException, AccountNotFoundException)
- `minibank*.java` - Main/demo classes for different lab iterations (e.g., `minibank13.java` is the latest)

## Compile & Run

From project root:

```bash
javac -cp . minibank13.java
java -cp . minibank13
```

## Run the JAR

```bash
java -jar minibank13.jar
```

## Notes
- Multiple class files and versions exist for lab progression.
- Uses Java 17.
