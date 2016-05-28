package com.seabattle.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int size;
    private List<List<Integer>> battlefield1;
    private List<List<Integer>> battlefield2;
    private List<Ship> shipList1;
    private List<Ship> shipList2;
    private String password;
    private String username;
    private Integer myScore;
    private Integer enemyScore;
    public Game(int size){
        myScore = 0;
        enemyScore = 0;
        battlefield1 = new ArrayList<List<Integer>>();
        battlefield2 = new ArrayList<List<Integer>>();
        shipList1 = createShips();
        shipList2 = createShips();
        for(int i = 0; i < size; i++){
            List<Integer> newList1 = new ArrayList<>();
            List<Integer> newList2 = new ArrayList<>();
            for(int j = 0; j < size; j++){
                newList1.add(new Integer(0));
                newList2.add(new Integer(0));
            }
            battlefield1.add(newList1);
            battlefield2.add(newList2);
        }
        this.size = size;
        setAllShipsRandom(battlefield1, shipList1);
        setAllShipsRandom(battlefield2, shipList2);
    }
    public void shoot(int x, int y){
        makeShoot(battlefield1, shipList1, x, y, true);
        Random random = new Random();
        do{
            x = random.nextInt(8);
            y = random.nextInt(8);
        }while(!checkShoot(x,y));
        makeShoot(battlefield2, shipList2, x, y, false);
    }
    public Boolean checkShoot(int x, int y){
        int value = battlefield2.get(x).get(y);
        if(value == 100 || value < 0)
            return false;
        return true;
    }
    public void makeShoot(List<List<Integer>> battlefield, List<Ship> shipList, int x, int y, Boolean playerShoot){
        int value = battlefield.get(x).get(y);
        Ship currentShip;
        if(value == 0){
            battlefield.get(x).set(y, 100);
        }else{
            battlefield.get(x).set(y, value*(-1));
            for(int i = 0; i < shipList.size(); i++){
                currentShip = shipList.get(i);
                if(currentShip.getId() == value){
                    shipList.get(i).minusHealth();
                    if(!currentShip.isAlive()){
                        if(!playerShoot) {
                            enemyScore++;
                        }else{
                            myScore++;
                        }
                        killShip(battlefield, currentShip);
                    }
                }
            }
        }
    }
    private void killShip(List<List<Integer>> battlefield, Ship ship){
        int x, y, id;
        id = ship.getId();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if(battlefield.get(i).get(j)==(-id)) {
                    x = j;
                    y = i;
                    Integer[] arr1 = {x - 1, x - 1, x, x + 1, x + 1, x + 1, x, x - 1};
                    Integer[] arr2 = {y, y - 1, y - 1, y - 1, y, y + 1, y + 1, y + 1};
                    for (int z = 0; z < 8; z++) {
                        if (arr1[z] >= 0 && arr1[z] < size && arr2[z] >= 0 && arr2[z] < size) {
                            if ((battlefield.get(arr2[z]).get(arr1[z]) == 0 || battlefield.get(arr2[z]).get(arr1[z]) == 100) && battlefield.get(arr2[z]).get(arr1[z]) != id){
                                battlefield.get(arr2[z]).set(arr1[z], 100);
                            }
                        }
                    }
                }
            }
        }
    }
    public Integer getMyScore() {
        return myScore;
    }

    public void setMyScore(Integer myScore) {
        this.myScore = myScore;
    }

    public Integer getEnemyScore() {
        return enemyScore;
    }

    public void setEnemyScore(Integer enemyScore) {
        this.enemyScore = enemyScore;
    }

    public String validateData(){
        String errorMessage = null;
        if(username==null ||username.length() < 5){
            errorMessage = "Uncorrect username. Username must have length more than 5.";
        }
        if(password==null ||password.length() < 5){
            errorMessage = "Uncorrect password. Password must have length more than 5.";
        }
        return errorMessage;
    }
    public void printBattlefield(List<List<Integer>> battlefield){
        for(int i = 0; i < battlefield.size(); i++){
            for(int j = 0; j < battlefield.get(i).size(); j++){
                System.out.print(battlefield.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private void setAllShipsRandom(List<List<Integer>> battlefield, List<Ship> shipList){
        int x, y = 0;
        int horizontal = 0;
        Ship currentShip = null;
        Boolean flag;
        Random random = new Random();
        Integer shipSize = 0;
        for(int i = 0; i<shipList.size(); i++){
            flag = false;
            currentShip = shipList.get(i);
            shipSize = currentShip.getSize();
            while(!flag){
                horizontal = (int)(random.nextDouble()*100);
                if(horizontal > 50){
                    currentShip.setHorizontal(true);
                }
                x = random.nextInt(size);
                y = random.nextInt(size);
                flag = setCurrentShip(battlefield, currentShip, x, y);
            }
            shipList.set(i, currentShip);
        }
    }
    private Boolean checkPoint(List<List<Integer>> battlefield, int x, int y, int id){
        Integer[] arr1 = {x-1, x-1, x , x+1, x+1, x+1, x, x-1};
        Integer[] arr2 = {y, y-1, y-1, y-1, y, y+1, y+1, y+1};
        for(int i = 0; i < 8; i++){
            if(arr1[i] >= 0 && arr1[i] < size && arr2[i] >= 0 && arr2[i] < size) {
                if (battlefield.get(arr2[i]).get(arr1[i]) != 0 && battlefield.get(arr2[i]).get(arr1[i]) != id) {
                    return false;
                }
            }
        }
        return true;
    }
    private Boolean setCurrentShip(List<List<Integer>> battlefield, Ship ship, int x, int y){
        List<Integer> newCoords = new ArrayList<>();
        for(int i = 0; i < ship.getSize(); i++){
            if(x < size && y <size && battlefield.get(y).get(x) == 0 && checkPoint(battlefield,x,y, ship.getId())){
                battlefield.get(y).set(x, ship.getId());
                if(ship.getHorizontal()){
                    newCoords.add(x);
                    x++;
                }else{
                    newCoords.add(y);
                    y++;
                }
            }else{
                for(int z = 0; z < newCoords.size(); z++){
                   if(ship.getHorizontal()){
                       battlefield.get(y).set(newCoords.get(z), 0);
                   }else{
                       battlefield.get(newCoords.get(z)).set(x, 0);
                   }
                }
                return false;
            }
        }
        return true;
    }
    private List<Ship> createShips(){
        List<Ship> ships = new ArrayList<>();
        Ship newShip = new Ship(1, 4);
        ships.add(newShip);
        newShip = new Ship(2, 3);
        ships.add(newShip);
        newShip = new Ship(3, 3);
        ships.add(newShip);
        newShip = new Ship(4, 2);
        ships.add(newShip);
        newShip = new Ship(5, 2);
        ships.add(newShip);
        newShip = new Ship(6, 2);
        ships.add(newShip);
        return ships;
    }

    public List<Ship> getShipList1() {
        return shipList1;
    }

    public void setShipList1(List<Ship> shipList1) {
        this.shipList1 = shipList1;
    }

    public List<Ship> getShipList2() {
        return shipList2;
    }

    public void setShipList2(List<Ship> shipList2) {
        this.shipList2 = shipList2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Integer>> getBattlefield1() {
        List<List<Integer>> battlefield = new ArrayList<List<Integer>>();
        int value;
        for(int i = 0; i < size; i++){
            List<Integer> newList = new ArrayList<>();
            for(int j = 0; j < size; j++){
                value = battlefield1.get(i).get(j);
                if(value > 0 && value != 100)
                    newList.add(0);
                else newList.add(value);
            }
            battlefield.add(newList);
        }
        return battlefield;
    }

    public void setBattlefield1(List<List<Integer>> battlefield1) {
        this.battlefield1 = battlefield1;
    }

    public List<List<Integer>> getBattlefield2() {
        return battlefield2;
    }

    public void setBattlefield2(List<List<Integer>> battlefield2) {
        this.battlefield2 = battlefield2;
    }
}
