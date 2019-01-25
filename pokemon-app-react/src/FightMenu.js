import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import FrontImg from './FrontImg';

export default class FightMenu extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
movelist:[]
        }
    }
    componentDidMount() {
        fetch('api/pokemon/'+this.props.level+'/'+this.props.name,{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({movelist:data.moveList}))
    }
    generatemoves =()=>{
         let pos = 0;
         let movelist=this.state.movelist;
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



                </tr>
                    
                
           )})
        return test;
    }  
    
    render() {
        return (
                <div className='col-game'>  
         

         <Container className="menu main-game-panel">
         <h2>{this.props.name}'s movelist</h2>
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

