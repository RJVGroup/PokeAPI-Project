import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import Shop from './Shop';
import Battle from './Battle';
import Choose from './Choose';
import FrontImg from './FrontImg';





export default class Roam extends Component {
    constructor(props) {
      super(props);
      this.bagClick=this.bagClick.bind(this);
      this.bagClose=this.bagClose.bind(this);
      this.pokemonClick=this.pokemonClick.bind(this);
      this.pokemonClose=this.pokemonClose.bind(this);
      this.shopClick=this.shopClick.bind(this);
      this.shopClose=this.shopClose.bind(this);
      this.toggle=this.toggle.bind(this);

    this.state = {
      party:'',
      cpokemon:'',
      epokemon:'',
      move:'',
      bag:false,
      pokemonParty:false,
      locationtext:'You set off to find pokémon and adventure!'
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
   shopClick(){
    this.setState({shop:true})
   }
   shopClose(){
    this.setState({shop:false})
   }
   toggle() {
    this.setState(prevState => ({
      shop: !prevState.shop
    }));
  }   
   chosenClick(){
    this.setState({chosen:true})
   }
    render() {
      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      const shop=this.state.shop;
      const locationtext=this.props.locationtext;
      const epokemon=this.state.epokemon;
      const cpokemon=this.state.cpokemon;

  
     
    

      return (
      
  
        <div className='col-game'>  
         <Container className="menu row-game">
        <h1 className=" main-game-panel">{locationtext}</h1>
         </Container> 
             <div className='row-game'>
             <Container className=" menu img-game-panel">      
             <div className="pokemonimg " >
        <img src={"https://66.media.tumblr.com/088786d466c3a315d6043b8e59d96770/tumblr_msu2ojWkqz1scncwdo1_500.gif"} /></div>
             </Container> 
             
             <Container className="menu main-game-panel" >
            <button className=" main-game-panel" onClick={this.props.move}>Move</button>           
             <button className=" main-game-panel" onClick={this.props.pokemonpartyToggle}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.props.bagToggle}>Bag</button>             
             <button className=" main-game-panel"  onClick={this.props.shopToggle}>Go to Shop</button>

             </Container > </div>
        </div>  
      );
     
   }
  }