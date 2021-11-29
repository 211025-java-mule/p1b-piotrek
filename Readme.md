<h1>Item application</h1>

<h3> Description </h3>

Application allows user to check probability of nationality based on their name. It is using local API:

> http://localhost:8081/

Localhost port of this application is <b>8080.

<h3>Features</h3>
After entering name using below endpoint:

Get method
> http://localhost:8080/items/name/{name}

results of searching is added to another application's database.

To see all of added entities please use:

Get method
> http://localhost:8080/items/

It is also possible to get only one country with the biggest probability:

Get method
> http://localhost:8081//items/name/one/{name}

Although please be informed that before using this endpoint name has to be already added

If you want to save your searching please use below endpoint:

> http://localhost:8081//items/save

Txt file will be saved to path:

```bash
C:\Users\Piortek\IdeaProjects\p1b-piotrek\out\output.txt
```

<h3>Tech stack</h3>

- Java 11
- Maven
- Spring-boot
- Lombok
- Junit
- MockMvc

<h3>Running</h3>

> mvn spring-boot:run

<h3>Author</h3>

Piotr Boczar