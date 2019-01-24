import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';
import StarterPokemon from './StarterPokemon';
import Buy from './Buy';



import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Shop extends Component {
    constructor(props) {
        super(props);
        this.buyClick=this.buyClick.bind(this);
        this.buyClose=this.buyClose.bind(this);
        this.sellClick=this.sellClick.bind(this);
        this.sellClose=this.sellClose.bind(this);
  
      this.state = {
        buy:false,
        sell:false
          };
    }
    buyClick(){
      this.setState({items:true})
     }
     buyClose(){
      this.setState({items:false})
     }
     sellClick(){
        this.setState({items:true})
       }
    sellClose(){
        this.setState({items:false})
     }

  render() {
    const buy=this.state.items;
    const sell=this.state.items;

        if(buy){
          return (
            <div className='col-game'> 
            <Buy close={this.buyClose}/> 
             </div>)}
    
    return (
      <div className="col-game">
    <Container className="menu main-game-panel" >      
    <button className=" main-game-panel" onClick={this.buyClick}>Buy</button>
     <button className=" main-game-panel"onClick={this.sellClick}>Sell</button>
     <button className=" main-game-panel" onClick={this.props.close}>Leave</button>    
     </Container>
      </div>
    );
  }
}