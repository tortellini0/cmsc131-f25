# Frederick Community College CMSC 131, Fall 2025


## Setup Instructions 

1. **Install VS Code**  
   Download and install from [https://code.visualstudio.com](https://code.visualstudio.com).

2. **Install the Dev Containers Extension**  
   Open VS Code → Extensions → search for "Dev Containers" → install.

3. **Clone This Repository**  
   ```bash
   git clone git@github.com:johnmdusel/fcc-cmsc131-f25.git
   cd fcc-cmsc131-f25
   ```

4. **Open in VS Code**
    - Launch VS Code in this folder.
    - When prompted, click **"Reopen in Container"** .
    - Wait while the environment builds.

5. **Verify Setup**
    Open a terminal inside VS Code and run:
    ```bash
    java --version
    javac --version
    ```
    You should see that Java is installed.

6. **Run a Sanity Check** 
    
    6.1 Open `src/exercises/week00/HelloWorld.java` and use the play button (Run Java) to run the main routine. A terminal window should appear with output like this:
    ```
    ubuntu@86f33e8e9bef:/workspaces/fcc-cmsc131-f25$  /usr/bin/env /usr/lib/jvm/java-21-openjdk-amd64/bin/java @/tmp/cp_a13exa6xrrypb4t31fvf2mssd.argfile exercises.week00.HelloWorld 
    Hello, World!
    ```
    (Your container ID will differ.)

    6.2 Open the Testing pane (flask icon on left margin). You should see the `exercises.week00` test populated in the window. Press the Run Tests button to run the tests and verify that they pass.


## Directory Structure

More subdirectories will be added as the term goes on, but this high-level structure will stay the same.

```
fcc-cmsc131-f15
├── .devcontainer  # development environment
│   ├── devcontainer.json
│   └── Dockerfile
├── README.md  # you are here
├── src  # source code
│   ├── exercises
│   │   └── week00
│   │       ├── HelloWorld.java
│   │       └── README.md
│   ├── lib  # reusable components live in here
│   │   └── Utils.java
├── test  # unit test code
│   ├── exercises
│   │   └── week00
│   │       └── HelloWorldTest.java
└── .vscode  # IDE settings
    └── settings.json
```
