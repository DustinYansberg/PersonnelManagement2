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
    salesforceUsername: string;
	salesforceFirstName: string;
    salesforceLastName:string;
	salesforceCommunityNickname:string;
	salesforceAlias:string;
	salesforceEmail: string;

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
        salesforceUsername: string,
        salesforceFirstName: string,
        salesforceLastName:string,
        salesforceCommunityNickname:string,
        salesforceAlias:string,
        salesforceEmail: string){
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
            this.salesforceUsername = salesforceUsername;
            this.salesforceFirstName = salesforceFirstName;
            this.salesforceLastName = salesforceLastName;
            this.salesforceCommunityNickname = salesforceCommunityNickname
            this.salesforceAlias = salesforceAlias;
            this.salesforceEmail = salesforceEmail;
        }
}
