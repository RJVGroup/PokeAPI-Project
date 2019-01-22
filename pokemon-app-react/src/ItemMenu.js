import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class ItemMenu extends Component {
    constructor(props) {
      super(props);
      this.itemsClick=this.itemsClick.bind(this);

    this.state = {items:true
    };
  }
  itemsClick(){
    this.setState({items:false})
   }
  componentDidMount() {
   
    }
  
    render() {
        const items=this.state.items;
        if(items){
      return (
  
        <div className='main-game'>  
         <Container className="menu main-game-panel">
             </Container> 
             <Container className="menu main-game-panel" >
             <button className=" main-game-panel"onClick={this.itemsClick}>Close</button>

             </Container> 
        </div>  
      );
    }}
  }