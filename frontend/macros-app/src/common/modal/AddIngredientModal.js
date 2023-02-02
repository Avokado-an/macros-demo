import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

export const AddIngredientModal = () => {
    const [isOpen, setOpen] = useState(false);

    const toggleModal = () => {
        setOpen(!isOpen);
    }

    const onSave = () => {
        setOpen(!isOpen);
        //send request to server
    }

    return (
        <div className="modal show" style={{ display: 'block', position: 'initial' }}>
            <Button className="margin-bot-20 m-top-20 width-140" onClick={toggleModal} variant="outline-success">Add Ingredient</Button>
            <Modal show={isOpen}>
                <Modal.Header>
                    <Modal.Title>Add Ingredient</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Weight in gramms</Form.Label>
                            <Form.Control
                                type="number"
                                placeholder="100 gr"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Food Item</Form.Label>
                            <Form.Select>
                                <option>Food Item</option>
                                <option>Food Item</option>
                                <option>Food Item</option>
                                <option>Food Item</option>
                            </Form.Select>
                        </Form.Group>
                    </Form>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="secondary" onClick={toggleModal}>Close</Button>
                    <Button variant="dark" onClick={onSave}>Save changes</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}