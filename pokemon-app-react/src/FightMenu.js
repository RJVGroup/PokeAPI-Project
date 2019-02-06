import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import FrontImg from './FrontImg';

export default class FightMenu extends Component {
    
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);

        this.state = {
            cpokemon:this.props.cpokemon,
            cPokemonIndex:this.props.cPokemonIndex

        }
    } 
    handleClick = (moveIndex) => {
        let cPokemonIndex=this.props.cPokemonIndex
        this.props.fightTurn(cPokemonIndex, moveIndex);
        this.props.pokemonStatus(cPokemonIndex);
        this.props.epokemonStatus();
        this.props.close()
        
    }
   
    generatemoves =()=>{
         let pos = 0;
         let movelist=this.props.cpokemon.moveList;
        var test = []
       
        movelist.forEach(function(arrayItem,arrayIndex,array){
            pos=pos++;
            test.push(
                <tr>
                <td>{array[arrayIndex].moveName}</td>
                <td>{array[arrayIndex].movePower}</td>
                <td>{array[arrayIndex].moveAccuracy}</td>
                <td>{array[arrayIndex].damageClass}</td>
                <td>{array[arrayIndex].moveType}</td>
                <td><button className=" main-game-panel"  onClick={(e) => this.handleClick(arrayIndex,e)}>Select Move</button></td>
                </tr>
                    
                
           )},this)
        return test;
    }  
    
    render() {
              const cpokemon=this.state.cpokemon

        return (
                <div className='col-game'>  
         

         <Container className="menu main-game-panel">
         <h2>{cpokemon.name}'s movelist</h2>
         <Table responsive>   
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Power</th>
                                <th>Accuracy</th>
                                <th>Class</th>
                                <th>Normal</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.generatemoves()}
                        </tbody>
                    </Table>
                    <button  onClick={this.props.close}>Go Back</button> 
        </Container> 
        </div>  
        )            
        }
}

