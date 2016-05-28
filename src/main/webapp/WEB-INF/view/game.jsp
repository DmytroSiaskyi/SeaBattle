<%@ page language="java"%>
<%@ page import="java.lang.*"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html ng-app="myApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="icon" type="image/png" href="/resources/images/ico/Elegantthemes-Beautiful-Flat-One-Color-Anchor.ico" />
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/resources/scripts/angular.min.js"></script>
    <script src="/resources/scripts/battlefield.js"></script>
    <title>Sea Battle</title>
</head>
<body ng-controller="battlefieldController">
<header><div class="name">Sea Battle</div></header>
<div class="game">
    <h1 style="color: black">Current game</h1>
    <div class="score">
        {{myScore}} : {{enemyScore}}
    </div>
    <div class="gameFields">
        <table border="1px solid" class="fields" id=enemyField">
            <caption>Enemy</caption>
            <tr>
            <tbody>
            <tr ng-repeat="row in myBattlefield1 track by $index">
                <td ng-repeat="cell in row track by $index" ng-switch on="cell">
                    <p ng-switch-when="0" ng-click="shoot(myBattlefield1.indexOf(row), $index)" class="freeButton"></p>
                    <p ng-switch-when="100" class="firedButton"></p>
                    <p ng-switch-default class="firedShipButton"></p>
                </td>
            </tr>
            </tbody>
        </table>

        <table border="1px solid" class="fields" id="myField">
            <caption>My table</caption>
            <tbody>
            <tr ng-repeat="row in myBattlefield2 track by $index">
                <td ng-repeat="cell in row track by $index" ng-switch on="cell">
                    <p ng-switch-when="100" class="firedButton"></p>
                    <p ng-switch-when="0" class="uncheckedCell"></p>
                    <p ng-switch-when="1" class="healthyShip"></p>
                    <p ng-switch-when="2" class="healthyShip"></p>
                    <p ng-switch-when="3" class="healthyShip"></p>
                    <p ng-switch-when="4" class="healthyShip"></p>
                    <p ng-switch-when="5" class="healthyShip"></p>
                    <p ng-switch-when="6" class="healthyShip"></p>
                    <p ng-switch-default class="firedShipButton"></p>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <form action="/play" method="post">
        <input class="button" name="reset" type="submit" value="Reset">
    </form>
</div>
<footer>Author: Syaskiy Dmitry</footer>
</body>
</html>