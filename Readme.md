
## Project Structure Overview

- **PrinttokensMainMethodTest.java**: JUnit test class for the main method. This file performs program-level testing and ensures edge coverage across all program paths.
- **PrinttokensNonMainMethodTest.java**: JUnit test class for non-main methods. This file conducts method-level unit testing, covering all possible edges within each method.
- **Printtokens.java**: The corrected version of `Printtokens.java`, with all known issues fixed.

---

## Steps to Execute JUnit Tests

1. **Open Eclipse IDE** on your computer.
2. **Create a new Java project** and name it `Printtokens`.
3. **Remove unnecessary files**: Delete any auto-generated `.java` files (e.g., `module-info.java`) under the `src` directory.
4. **Create a new package**:
   - In the `src` directory, create a new package named `junitTesting`.
5. **Import source files**:
   - Import the following files into the `junitTesting` package:
     - `PrinttokensMainMethodTest.java`
     - `PrinttokensNonMainMethodTest.java`
     - `Printtokens.java`
6. **Add JUnit 5 Library**:
   - After importing, you'll see errors related to JUnit. 
   - To resolve this, hover over the `@Test` or `@Before` annotations in `PrinttokensMainMethodTest.java` or `PrinttokensNonMethodTest.java`.
   - A prompt will appear to add the JUnit 5 library; click to add it.
7. **Resolve Errors**: After adding the JUnit 5 library, the errors will be resolved, and the project will be ready for testing.
8. **Run the Tests**:
   - Right-click on the `junitTesting` package.
   - Select **Coverage As** -> **1 JUnit Test** to run the tests and generate a code coverage report.
9. **Export Code Coverage Report**:
   - Go to **File** -> **Export** -> **Run/Debug** -> **Coverage Session**.
   - Select the coverage session you wish to export.
   - Choose **HTML report packed into a zip file** and select the destination folder where the report will be generated.
