console.log("Hello my typescript app!");
interface Person{
    age: number
    name: string
}
let myPerson: Person = {
    age: 29,
    name: "Ivsen"
}

class Animal {
    name: string;
    constructor(theName: string) { this.name = theName; }
    move(distanceInMeters: number = 0) {
        console.log(`${this.name} moved ${distanceInMeters}m.`);
    }
}

class Snake extends Animal {
    constructor(name: string) { super(name); }
    move(distanceInMeters = 5) {
        console.log("Slithering...");
        super.move(distanceInMeters);
    }
}
let sam = new Snake("Sammy the Python");
sam.move();
console.log(sam.name);

function buildName(firstName: string, lastName: string) {
    return firstName + " " + lastName;
}

console.log(buildName("Anna", "Goranova"));



let confirmed: boolean = false;


console.log(JSON.stringify(myPerson));


interface PersonConfig {
    name?: string;
    age?: number;
}

function createPerson(config: PersonConfig): {name: string; age: number} {
    let newPerson = {name: "John", age: 18};
    if (config.name) {
        newPerson.name = config.name;
    }
    if (config.age) {
        newPerson.age = config.age;
    }
    return newPerson;
}

let myConfigedPerson = createPerson({name: "Adam"});