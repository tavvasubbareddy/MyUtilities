This project contains miscellaneous utilities that would be required in automation. These utilities work seamlessly and produce consistent results irrespective of operating system.

### FileCheckSumGenerator.java
Generates checksum value of a file using MessageDigest algorithms (MD5/SHA-1/SHA-256)

**Input:** Path of the file. Ex: c:\test\abc.txt

**Output:** Checksum value

------------

### SSORequestsSender.java
Sends multiple subsequent requests under same session. It helps to automate CRUD (Create, Read, Update & Delete) operations. It helps to automate SSO based scenarios.

**Input:** Server url

**Output:**  Response from the server.