import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';


export default class StarterPokemon extends Component {
    constructor(props) {
        super(props);
        this.chooseStarter = this.chooseStarter.bind(this);
      this.state = {  

    }
    }
    chooseStarter() {
      fetch('/api/player/choose-starter/'+this.props.pokemon.name,{
        method: 'POST',
      });
      }
    
    
    
      render() {
        return (
           <button className="menu main-game-panel" onClick={this.chooseStarter} style={this.props.pokestyle}>
              <FrontImg id={this.props.pokemon.id}/>
                NAME: {this.props.pokemon.name}
                 <br/> 
                TYPE: {this.props.pokemon.types}
                <br/>
                LEVEL: {this.props.pokemon.level}
                <br/>
            </button>
        )
      }
    }