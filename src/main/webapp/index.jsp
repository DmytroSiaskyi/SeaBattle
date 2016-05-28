<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page language="java"%>
<%@ page import="java.lang.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="icon" type="image/png" href="/resources/images/ico/Elegantthemes-Beautiful-Flat-One-Color-Anchor.ico" />
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="/resources/scripts/angular.min.js"></script>
    <script src="/resources/scripts/mainPage.js"></script>
    <title>Sea Battle</title>
</head>
<body ng-app="myApp" ng-controller="mainPageController">
    <div class="background" ng-click="showGameConfig()" ng-show="showConfig"></div>
    <div class="background" ng-click="showSaveConfig()" ng-show="showMenu"></div>
    <div class="indexBody">
    <header><div class="name">Sea Battle</div></header>
    <div class="controls">
        <h1>Start playing new version!</h1>
        <h1>Updated ships position generating, css, and more another.</h1>
        <h2>Try new opportunities</h2>
        <button id="newGame" class="button" ng-click="showGameConfig()">New game</button>
        <button class="button" ng-click="showSaveConfig()">Continue</button>
        <div class="gameConfig" ng-show="showConfig">
            <form action="/index" method="post" class="configForm">
                <h2>Game settings</h2>
                <h3>Username</h3>
                <input type="text" name="username" placeholder="username" ng-model="myGame.username">
                <h3>Ships position</h3>
                <input type="radio" value="Auto" name="autogen">Auto
                <input type="radio" value="Manual" name="autogen">Manual
                <h3>Game password</h3>
                <input type="password" placeholder="password" name="password" ng-model="myGame.password">
                <br>
                <input class="button" name="submit" type="submit" value="Start" ng-click="createNewGame($event, 'Start')">
                <div class="errorMessage">{{errorMessage}}</div>
            </form>
        </div>
        <div class="gameConfig" ng-show="showMenu">
            <form action="/index" method="post" class="configForm">
                <h2>Continue game</h2>
                <h3>Username</h3>
                <input type="text" name="username" placeholder="username">
                <h3>Game password</h3>
                <input type="password" placeholder="password" name="password">
                <br>
                <input class="button" name="submit" type="submit" value="Continue">
            </form>
        </div>
    </div>
    </div>
    </div>
</body>
</html>