import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import FightMenu from './FightMenu';

import FrontImg from './FrontImg';
import BackImg from './BackImg';



export default class Battle extends Component {
    constructor(props) {
      super(props);
      this.fightClick=this.fightClick.bind(this);
      this.fightClose=this.fightClose.bind(this);
      this.bagClick=this.bagClick.bind(this);
      this.bagClose=this.bagClose.bind(this);
      this.pokemonClick=this.pokemonClick.bind(this);
      this.pokemonClose=this.pokemonClose.bind(this);

    this.state = {
      epokemon:this.props.epokemon,
      cpokemon:this.props.cpokemon,

      fight:false,
      bag:false,
      pokemon:false
    };
  }
 
  fightClick(){
    this.setState({fight:true})
   }
  fightClose(){
    this.setState({fight:false})
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
   
  
  
    render() {
      const fight=this.state.fight;
      const epokemon=this.state.epokemon
      const cpokemon=this.state.cpokemon

      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      
      if(fight){
        return (
          <div className='col-game'> 
          <FightMenu level={cpokemon.level} name={cpokemon.name} close={this.fightClose}/> 
           </div>)
      }
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
         <div className="main-game-panel"  >  
         <BackImg id={cpokemon.id}/>
         {cpokemon.name} Level:{cpokemon.level}
         <br/>
         HP:{cpokemon.currentHP}</div>        
         <br/>

         <div className="main-game-panel"  >  
         <FrontImg id={epokemon.id}/>
         {epokemon.name} Level:{epokemon.level}
         <br/>
         HP:{epokemon.currentHP}</div>
         </Container> 
             <div className='row-game'>
             <Container className="menu main-game-panel" > 
             <h1>{this.props.location}</h1>    
             <h1>You encounter a {epokemon.name} pokémon</h1>
             </Container> 
             <Container className="menu main-game-panel" >      
             <button className=" main-game-panel"  onClick={this.fightClick}>Fight</button>
             <button className=" main-game-panel" onClick={this.pokemonClick}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.bagClick}>Bag</button>
             <button className=" main-game-panel"onClick={this.props.close}>Run</button>
             </Container> </div>
        </div>  
      );
     } 
    
  }