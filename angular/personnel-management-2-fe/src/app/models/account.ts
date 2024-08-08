export class Account {

    accountAppId: string;
    accountUserId: string;
	nativeIdentity: string;
	displayName: string;
	instanceId: string;
	password: string;
	currentPassword: string;
	active: boolean;
	locked: boolean;
	manuallyCorrelated: boolean;
	hasEntitlements: boolean;
    accountAppName: string;
	appSpecificProperties: string;

    constructor(accountAppId: string,
        accountUserId: string,
        nativeIdentity: string,
        displayName: string,
        instanceId: string,
        password: string,
        currentPassword: string,
        active: boolean,
        locked: boolean,
        manuallyCorrelated: boolean,
        hasEntitlements: boolean,
        accountAppName: string,
        appSpecificProperties: string){
            this.accountAppId = accountAppId;
            this.accountUserId = accountUserId;
	        this.nativeIdentity = nativeIdentity;
            this.displayName = displayName;
            this.instanceId = instanceId;
            this.password = password;
            this.currentPassword = currentPassword;
            this.active = active;
            this.locked = locked;
            this.manuallyCorrelated = manuallyCorrelated;
            this.hasEntitlements = hasEntitlements;
            this.accountAppName = accountAppName;
            this.appSpecificProperties = appSpecificProperties;
        }
}
