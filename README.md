# WordCountApp

WordCountApp is a simple Java console application that computes word occurrences from a text file and sorts them by frequency.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Briefly introduce your project. What does it do? Why did you create it?

## Features

- The program uses SLF4J for logging.
- It takes input file paths until the user enters "exit" to end the session.
- The word count is case-insensitive and ignores punctuation.
- The word counts are displayed in descending order of occurrences.
- JUnit test included

## Prerequisites

Specify any prerequisites or dependencies that users need to have installed before using your application.

- Java (version 21.0.1)
- Maven (version 3.9.6)

## Getting Started

Provide instructions on how to set up the project locally. Include any steps required for installing dependencies or configuring the environment.

```bash
# Clone the repository
git clone https://github.com/your-username/WordCountApp.git

# Navigate to the project directory
cd WordCountApp

# Build the project using Maven
mvn clean install
```

## Usage
Explain how to use your application. Include examples and command-line instructions.

# Run the WordCountApp

```bash
java -jar .\target\word-count-cli-1.0.jar
```


##  License
Specify the license under which your project is distributed.

MIT License
