## How to use the application locally:
* Generate certificates using the following script and place them to the `resources/certs` folder:
    ```
    # create rsa key pair
    openssl genrsa -out keypair.pem 2048 && 
    # extract public key
    openssl rsa -in keypair.pem -pubout -out public.pem && 
    # create private key in PKCS#8 format
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
    ```
* Start the application using the following command:
    ```
    docker-compose up -d
    ```
* Stop the application using the following command:
    ```
    docker-compose down
    ```