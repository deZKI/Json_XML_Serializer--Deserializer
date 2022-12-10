//https://habr.com/ru/company/usetech/blog/665046/
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper

//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData
//import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.io.File


class Address() {
    var countryCode: Int? = null
    var city: String? = null
    var street: String? = null

    constructor(countryCode: Int, city: String, street: String) : this() {
        this.countryCode = countryCode
        this.city = city
        this.street = street
    }

    override fun toString(): String {
        return "Address " +
                "countryCode=" + countryCode +
                ", city='" + city + '\'' +
                ", street='" + street + '\''
    }
}

class Person() {
    var firstName: String? = null
    var lastName: String? = null
    var address: Address? = null

    constructor(firstName: String, lastName: String, address: Address) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.address = address
    }

    override fun toString(): String {
        return "Person " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address;
    }
}

//вместо рута использовал arraylist
//class Root(){
//    //https://stackoverflow.com/questions/49155318/deserializing-xml-with-list-using-jackson
//    @JacksonXmlProperty(localName = "Person")
//    @JacksonXmlCData
//    @JacksonXmlElementWrapper(useWrapping = false)
//    var persons: Array<Person>?=null
//    constructor(persons: Array<Person>):this()
//    {
//        this.persons=persons
//    }
// }
val person = Person(
    "Вася", "Пупкин",
    Address(7, "Н", "Бассейная")
);
val person1 = Person(
    "Иван", "Пупкин",
    Address(7, "Н", "Бассейная")
);
val person2 = Person(
    "Коля", "Пупкин",
    Address(7, "Н", "Бассейная")
);
val Persons = arrayListOf<Person>(person, person1, person2)
fun main() {
    println("XML:")
    xml()
    println("========================================================================================")
    println("Json:")
    json()
}


fun xml() {
    val om = XmlMapper()

    om.writeValue(
        File("/Users/kirill201/Documents/lab_6/src/main/kotlin/test_xml_serialize.xml"),
        Persons
    ); // преобразование в xml сериализация

    val xml = (om.readValue(
        File("/Users/kirill201/Documents/lab_6/src/main/kotlin/MyXML.xml"),
        Array<Person>::class.java
    )); //десериализация

    for (i in xml) { //дессериализация
        println(i)
    }
}

//https://stackoverflow.com/questions/19333106/issue-with-parsing-the-content-from-json-file-with-jackson-message-jsonmappin
fun json() {
    val om = jsonMapper()
    om.writeValue(
        File("/Users/kirill201/Documents/lab_6/src/main/kotlin/test_json_serialize.json"),
        Persons
    );//сериализация
    val json = (om.readValue(
        File("/Users/kirill201/Documents/lab_6/src/main/kotlin/MyJSON.json"),
        Array<Person>::class.java
    )); //десериализация
    for (i in json) { //дессериализация
        println(i)
    }
//    val read = (om.readValue(json,  Person::class.java));//десериализация
//    System.out.printf("Read person: %s\n", read);
}
