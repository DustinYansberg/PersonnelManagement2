export class User {

    userId: number;
    username: string;
    firstName: string;
    lastName: string;
    displayName: string;
    email: string;
    managerId: number;
    type: string;

    constructor(userId: number, username: string, firstName: string, lastName: string, displayName: string, email: string, managerId: number, type: string){
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.email = email;
        this.managerId = managerId;
        this.type = type;
    }

}
