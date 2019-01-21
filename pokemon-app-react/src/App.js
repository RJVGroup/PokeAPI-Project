import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Choose from './Choose';


class App extends Component {

  render() {
    return (
      <div className="App">
      <Choose/>
      </div>
    );
  }
}

export default App;
