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
* Create `.env` file in the same directory as the docker compose file, it should look something like this:
    ```
    SPRING_DATASOURCE_URL=jdbc:postgresql://database:5432/psychological-help-database
    SPRING_DATASOURCE_USERNAME=username
    SPRING_DATASOURCE_PASSWORD=password
  
    SPRING_MAIL_USERNAME=username
    SPRING_MAIL_PASSWORD=password
  
    POSTGRES_USER=user
    POSTGRES_PASSWORD=password
    POSTGRES_DB=psychological-help-database
    ```
* Start the application using the following command:
    ```
    docker-compose up -d
    ```
* Stop the application using the following command:
    ```
    docker-compose down
    ```