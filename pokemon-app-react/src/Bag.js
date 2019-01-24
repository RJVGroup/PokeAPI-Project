import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Bag extends Component {
    constructor(props) {
      super(props);
    };
  
  
  componentDidMount() {
   
    }
  
    render() {
      return (
  
        <div className='col-game'>  
         <Container className="menu main-game-panel">
             </Container> 
             <Container className="menu main-game-panel" >
             <button className=" main-game-panel"onClick={this.props.close}>Close</button>

             </Container> 
        </div>  
      );
    }
  }