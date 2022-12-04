## How to run the application locally:
* Start the database using the following script:
    ```
    docker run --name psychological-help-database -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=psychological-help-database -d postgres
    ```
* Generate certificates using the following script and place them to the `resources/certs` folder:
    ```
    # create rsa key pair
    openssl genrsa -out keypair.pem 2048 && 
    # extract public key
    openssl rsa -in keypair.pem -pubout -out public.pem && 
    # create private key in PKCS#8 format
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
    ```
* Start the application