package BusinessLogic;

public class VehicleProperties {
	WeaponPopulationAdministrator weaponAdministrator;
    WeaponProperties currentWeapon;

    public VehicleProperties() {
        weaponAdministrator = new WeaponPopulationAdministrator();
        currentWeapon = weaponAdministrator.generateDefaultWeapon();
        weaponAdministrator.addWeapon(currentWeapon);
    }

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
    
    public void generateWeapon(WeaponProperties foundWeapon){
        //supposing weaponAdministrator.generateDefaultWeapon() is the weapon found in the road
        currentWeapon = weaponAdministrator.generateWeapon(foundWeapon);
        weaponAdministrator.addWeapon(currentWeapon);
    }
}
