package BusinessLogic;

public class Vehicle extends PathObject{
	WeaponPopulationAdministrator weaponAdministrator;
    WeaponProperties currentWeapon;

    public Vehicle(double axisX, double axis) {
        super(axisX, axis);
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
    
    public void generateWeapon(){
        //supposing weaponAdministrator.generateDefaultWeapon() is the weapon found in the road
        currentWeapon = weaponAdministrator.generateWeapon(weaponAdministrator.generateDefaultWeapon());
        weaponAdministrator.addWeapon(currentWeapon);
    }
}
