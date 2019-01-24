import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import FrontImg from './FrontImg';

export default class PokemonParty extends Component {
    
    constructor(props) {
        super(props);

        this.state = {
            party: [],

        }
    }


    componentDidMount() {
        fetch('api/player/show-party',{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({party:data}))
    }
  
    generatelist =()=>{
         let pos = 0;
         let party=this.state.party;
        var test = []
        var bag=''
       

        party.forEach(function(arrayItem,arrayIndex,array){
            pos=pos++;
            test.push(
                <tr>
                <td><FrontImg id={array[arrayIndex].id}/></td>
                <td>{array[arrayIndex].name}</td>
                <td>{array[arrayIndex].types}</td>
                <td>{array[arrayIndex].level}</td>
                <td>{array[arrayIndex].currentHP}/{array[arrayIndex].hp}</td>




                </tr>
                    
                
           )})
        return test;
    }  
   
    
    render() {
        return (
                <div className='col-game'>  
         

         <Container className="menu main-game-panel">
         <button  onClick={this.props.close}>Go Back</button> 
         <h2>Choose your Pokémon</h2>      

         <br/>
         <Table responsive>   
                        <thead>
                            <tr>
                            <th/>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Level</th>
                                <th>HP</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.generatelist()}
                        </tbody>
                    </Table>   
        </Container> 
        </div>  
        )            
        }
}
