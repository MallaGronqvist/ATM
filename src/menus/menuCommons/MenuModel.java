package menus.menuCommons;

import java.util.List;

public abstract class MenuModel {
    protected List<String> options;

    public List<String> getMenuOptions(){
        return options;
    }

    protected abstract void processOption(int selectedOption) throws IndexOutOfBoundsException;
}
