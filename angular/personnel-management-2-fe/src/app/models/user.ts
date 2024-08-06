export class User {

    userId: string;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    displayName: string;
    email: string;
    managerId: string;
    manager: string
    type: string;
    softwareVersion: string;
    administrator: string;
    administratorId: string;
    active: boolean;
    department: string;

    constructor(userId: string, username: string, password: string,firstName: string, lastName: string, displayName: string, email: string, managerId: string, manager: string, type: string, softwareVersion: string, administrator: string, administratorId: string, active: boolean, department: string){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.email = email;
        this.managerId = managerId;
        this.manager = manager;
        this.type = type;
        this.softwareVersion =softwareVersion;
        this.administrator =administrator;
        this.administratorId = administratorId;
        this.active = active;
        this.department =department;
    }

}
