package pairmatching.controller;

import pairmatching.Initialize.Initializer;
import pairmatching.service.MainService;
import pairmatching.validation.MenuValidation;
import pairmatching.view.InputView;
import pairmatching.view.Message.ErrorMessage;

import java.io.IOException;

import static pairmatching.view.Main.MainMenu.printMainMenu;

public class MainController {
    private final Initializer initializer;
    private final MenuValidation menuValidation;
    private final MainService mainService;


    public MainController() {
        this.initializer = new Initializer();
        this.menuValidation = new MenuValidation();
        this.mainService = new MainService();
    }

    public void set() throws IOException {
        initializer.setBackCrew();
        initializer.setFrontCrew();
        initializer.setMissions();
    }

    public void run() {
        boolean runStatus = true;
        while (runStatus) {
            try {
                printMainMenu();
                String input = InputView.input();
                menuValidation.checkNull(input);
                runStatus = selectMenu(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean selectMenu(String input) {
        if (input.equals("1")) {
            mainService.match();
            return true;
        }
        if (input.equals("2")) {
            mainService.getPairList();
            return true;
        }
        if (input.equals("3")) {
            mainService.removePairList();
            return true;
        }
        if (input.equals("Q")) {
            return false;
        }
        throw new IllegalArgumentException(ErrorMessage.ERROR + ErrorMessage.NO_MENU);
    }

}
