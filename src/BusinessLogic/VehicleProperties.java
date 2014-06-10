package BusinessLogic;

public class VehicleProperties {
	WeaponPopulationAdministrator weaponAdministrator;
    WeaponProperties currentWeapon;

    public VehicleProperties() {
        weaponAdministrator = new WeaponPopulationAdministrator();
        currentWeapon = weaponAdministrator.generateDefaultWeapon();
        weaponAdministrator.addWeapon(currentWeapon);
    }
    
    public void generateWeapon(WeaponProperties foundWeapon){
    	try
    	{
	        currentWeapon = weaponAdministrator.generateWeapon(foundWeapon);
	        weaponAdministrator.addWeapon(currentWeapon);
    	}
    	catch(Exception e)
    	{
    		System.out.println("ERROR: VehicleProperties.generateWeapon() failure");
    	}
    }
    
    //getters and setters
    public WeaponPopulationAdministrator getWeaponAdministrator() {
        return weaponAdministrator;
    }

    public void setWeaponAdministrator(WeaponPopulationAdministrator weaponAdministrator) {
        this.weaponAdministrator = weaponAdministrator;
    }

    public WeaponProperties getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(WeaponProperties currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
}
