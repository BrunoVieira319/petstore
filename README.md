# Tomcat

Sistema da Pet Store com Tomcat

- Faça o download do Tomcat.
- Entre na pasta do projeto e crie o .war com o comando:
```
./gradlew war
```
- Coloque o .war que foi gerado, dentro da pasta webapp do Tomcat.
```
cp build/libs/pet-store-1.0.war <caminho para o Tomcat>/webapps/
```
- No diretório raíz do Tomcat execute o comando:
```
bin/catalina.sh start
```

### Endpoint
```
<GET> localhost:8080/pet-store-1.0/pets
```
