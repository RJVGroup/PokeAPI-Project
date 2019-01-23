import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import ItemsMenu from './ItemMenu';
import ItemMenu from './ItemMenu';

export default class Battle extends Component {
    constructor(props) {
      super(props);
      this.itemsClick=this.itemsClick.bind(this);
      this.itemsClose=this.itemsClose.bind(this);

    this.state = {
      items:false
    };
  }
  itemsClick(){
    this.setState({items:true})
   }
   itemsClose(){
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
             <button className=" main-game-panel"onClick={this.itemsClose}>Close</button>

             </Container> 
        </div>  );}
        return(
  
        <div className='main-game'>  
         <Container className="menu choose-game">
         <div className="pokemonimg main-game-panel"  >  
         <img src={"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"+1+".png"}/>
         HP</div>
         <br/>
         <div className="pokemonimg main-game-panel"  >  
         <img src={"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+1+".png"}/>
         HP</div>
         
             </Container> 
             <Container className="menu main-game-panel" >      
             <button className=" main-game-panel">Fight</button>
             <button className=" main-game-panel">Pokemon</button>
             <button className=" main-game-panel" onClick={this.itemsClick}>Bag</button>
             <button className=" main-game-panel">Run</button>
             </Container> 
        </div>  
      );
     } 
  }