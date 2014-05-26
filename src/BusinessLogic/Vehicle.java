package BusinessLogic;

public class Vehicle extends PathObject{
	WeaponPopulationAdministrator weaponAdministrator;
    Weapon currentWeapon;

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

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }
    
    public void generateWeapon(){
        //supposing weaponAdministrator.generateDefaultWeapon() is the weapon found in the road
        currentWeapon = weaponAdministrator.generateWeapon(weaponAdministrator.generateDefaultWeapon());
        weaponAdministrator.addWeapon(currentWeapon);
    }
}
