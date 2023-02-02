import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

export const EditPersonalDataModal = ({ initialData }) => {
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
            <Button className="margin-bot-20" onClick={toggleModal} variant="dark">Edit</Button>
            <Modal show={isOpen}>
                <Modal.Header>
                    <Modal.Title>Edit User Data</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control
                                defaultValue={initialData.email}
                                type="email"
                                placeholder="name@example.com"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Height</Form.Label>
                            <Form.Control
                                defaultValue={initialData.height}
                                type="number"
                                placeholder="180 sm"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Weight</Form.Label>
                            <Form.Control
                                defaultValue={initialData.weight}
                                type="number"
                                placeholder="80 kg"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Aim Weight</Form.Label>
                            <Form.Control
                                defaultValue={initialData.aimWeight}
                                type="number"
                                placeholder="70 kg"
                                autoFocus
                            />
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