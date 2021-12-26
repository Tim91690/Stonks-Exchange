package fr.tim.smpbank;

import fr.tim.smpbank.bank.Bank;

import fr.tim.smpbank.bank.CalculTaux;
import fr.tim.smpbank.bank.Taux;
import fr.tim.smpbank.commands.*;
import fr.tim.smpbank.files.Autosave;
import fr.tim.smpbank.files.FileManager;
import fr.tim.smpbank.listeners.ListenerManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class smpBank extends JavaPlugin {

    public static smpBank plugin;
    public Taux taux;

    @Override
    public void onEnable() {
        ListenerManager.registerEvents(this);
        registerCommands();
        new FileManager();
        Autosave.loop();

        this.taux = new Taux();


    }

    @Override
    public void onDisable() {
        for (Bank bank : Bank.bankList.values()) {
            bank.logBankState();
        }
    }

    @Override
    public void onLoad() {
        plugin = this;
    }

    public static smpBank getPlugin() {
        return plugin;
    }

    public Taux getTaux() {
        return this.taux;
    }

    private void registerCommands() {
        getCommand("bank").setExecutor(new Gui());
        getCommand("forcesave").setExecutor(new Forcesave());
        getCommand("deposit").setExecutor(new Deposit());
        getCommand("withdraw").setExecutor(new Withdraw());

    }
}
