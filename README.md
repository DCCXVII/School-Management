# JavaFX Maven Project

This is a simple JavaFX project created using Maven. It includes basic setup for a JavaFX application with an MVC architecture.

## Prerequisites

- Java 8 or higher
- Maven
- JavaFX
- Css

## Getting Started

0. **Install maven and javafx sdk**
   ```
   https://maven.apache.org/download.cgi
   ```
   create a maven environement variable ```MAVEN_HOME``` then set location to it.

   for javafx you can use two options

   - by installing javafx sdk, follow this:
   ``` https://openjfx.io/openjfx-docs/#install-javafx ```

   - or, run it in vscode:

   ```https://gluonhq.com/products/javafx/```


1. **Clone the repository:**

   ```bash
   git clone https://github.com/DCCXVII/School-Management
   ```

2. **Navigate to the project directory:**

   ```bash
   cd School-Management/gestionens
   ```

3. **Build the project using Maven:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn clean javafx:run
   ```

## Configuration

The project includes configuration files for the application in the `src/main/resources/master/config` directory. These files are used to manage application settings and properties.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License.
