import logo from './logo.svg';
import React from 'react';
import './App.css';
import './components/ListEmployeeComponent.jsx'
import './components/CreatedEmployeeComponent'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListEmployeeComponent from './components/ListEmployeeComponent.jsx';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreatedEmployeeComponent from './components/CreatedEmployeeComponent.jsx';
import UpdatedEmployeeComponent from './components/UpdatedEmployeeComponent';
import ViewEmployeeComponent from './components/ViewEmployeeComponent';

function App() {
  return (
    <div>
    <Router>
    <div className="container">
    <HeaderComponent/>
    <div className="container">
      <Switch>
        <Route path = "/" exact component = {ListEmployeeComponent}></Route>
        <Route path = "/employees" exact component = {ListEmployeeComponent}></Route>
        <Route path = "/add-employee" component = {CreatedEmployeeComponent}></Route>
        <Route path = "/update-employee/:id" component = {UpdatedEmployeeComponent}></Route>
        <Route path = "/view-employee/:id" component = {ViewEmployeeComponent}></Route>
      </Switch>
    </div>
    <FooterComponent/>
    </div>
     </Router>
    </div>
  );
}

export default App;
