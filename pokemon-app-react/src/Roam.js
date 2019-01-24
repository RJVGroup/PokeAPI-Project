import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import Shop from './Shop';
import Battle from './Battle';




export default class Roam extends Component {
    constructor(props) {
      super(props);
      this.bagClick=this.bagClick.bind(this);
      this.bagClose=this.bagClose.bind(this);
      this.pokemonClick=this.pokemonClick.bind(this);
      this.pokemonClose=this.pokemonClose.bind(this);
      this.shopClick=this.shopClick.bind(this);
      this.shopClose=this.shopClose.bind(this);
      this.move=this.move.bind(this);
      this.run=this.run.bind(this);

    this.state = {
      currentpokemon:[],
      location:[],
      bag:false,
      pokemon:false,
      shop:false,
      name:'',
      level:'',
    };
  }

  move() {
    fetch('api/player/move',{method: 'POST'})
      .then(response => response.json())
      .then(data=>this.setState({location:data}))
    if(this.state.location.pokemonEncountered!=null){this.setState({ name:this.state.location.pokemonEncountered.name, level:this.state.location.pokemonEncountered.level})} 
    else{this.setState({ name:'', level:''})}
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
   shopClick(){
    this.setState({shop:true})
   }
   shopClose(){
    this.setState({shop:false})
   }
   run(){
    this.setState({name:''})
   }
    render() {
      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      const shop=this.state.shop;
      const location=this.state.location.name;
      const name=this.state.name;
      const level=this.state.level;


     if(name!=''){
        return (
          <div className='col-game'> 
          <Battle ename={name} elevel={level} close={this.run}/> 
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
    if(shop){
      return (
        <div className='col-game'> 
        <Shop close={this.shopClose}/> 
         </div>)
    }
      return (
      
  
        <div className='col-game'>  
         <Container className="menu row-game">
        You went to the {location}
         </Container> 
             <div className='row-game'>
             <Container className=" menu img-game-panel">      
             <div className="pokemonimg " >
        <img src={"https://66.media.tumblr.com/088786d466c3a315d6043b8e59d96770/tumblr_msu2ojWkqz1scncwdo1_500.gif"} /></div>
             </Container> 
             <Container className="menu main-game-panel" >      
            <button className=" main-game-panel" onClick={this.move}>Move</button>           
             <button className=" main-game-panel" onClick={this.pokemonClick}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.bagClick}>Bag</button>             
             <button className=" main-game-panel"  onClick={this.shopClick}>Go to Shop</button>

             </Container> </div>
        </div>  
      );
    }
  }