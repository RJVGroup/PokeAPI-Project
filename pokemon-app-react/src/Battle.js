import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import FrontImg from './FrontImg';
import BackImg from './BackImg';



export default class Battle extends Component {
    constructor(props) {
      super(props);
      this.bagClick=this.bagClick.bind(this);
      this.bagClose=this.bagClose.bind(this);
      this.pokemonClick=this.pokemonClick.bind(this);
      this.pokemonClose=this.pokemonClose.bind(this);

    this.state = {
      bag:false,
      pokemon:false
    };
  }
  bagClick(){
    this.setState({bag:true})
   }
   bagClose(){
    this.setState({bag:false})
   }
   pokemonClick(){
    this.setState({pokemon:true})
   }
   pokemonClose(){
    this.setState({pokemon:false})
   }
  componentDidMount() {
   
    }
  
  
    render() {
      const bag=this.state.bag;
      const pokemon=this.state.pokemon;

    if(bag){
      return (
        <div className='col-game'> 
        <Bag close={this.bagClose}/> 
         </div>)
    }
     if(pokemon){
      return (
        <div className='col-game'> 
        <PokemonParty close={this.pokemonClose}/> 
         </div>)
    }
    
        return(
  
        <div className='col-game'>  
         <Container className="menu row-game">
         <div className="pokemonimg main-game-panel"  >  
         <BackImg id='1'/>
         HP</div>
         <br/>
         <div className="pokemonimg main-game-panel"  >  
         <FrontImg id='1'/>
         HP</div>
         </Container> 
             <div className='row-game'>
             <Container className="menu main-game-panel" >      
             Text
             </Container> 
             <Container className="menu main-game-panel" >      
             <button className=" main-game-panel">Fight</button>
             <button className=" main-game-panel" onClick={this.pokemonClick}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.bagClick}>Bag</button>
             <button className=" main-game-panel">Run</button>
             </Container> </div>
        </div>  
      );
     } 
    
  }