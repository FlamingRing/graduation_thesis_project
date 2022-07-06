from flask import Flask, request
import torch
from models import gan_2_4, gan_8_15
import scipy.misc
import random


class Gan2(object):
    def __init__(self):
        self.generator = gan_2_4.generator(class_num=2).cuda()
        self.generator.load_state_dict(torch.load('./pretrained_weights/gan_2.pkl'))
        self.generator.eval()

    def generate(self, embed):
        noise = torch.rand(1, 100).cuda()
        label = torch.tensor([embed], dtype=torch.float).cuda()
        image_tensor = self.generator(noise, label)
        sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
        scipy.misc.imsave('./temporary_images/face2.png', sample[0])


class Gan4(object):
    def __init__(self):
        self.generator = gan_2_4.generator(class_num=4).cuda()
        self.generator.load_state_dict(torch.load('./pretrained_weights/gan_4.pkl'))
        self.generator.eval()

    def generate(self, embed):
        noise = torch.rand(1, 100).cuda()
        label = torch.tensor([embed], dtype=torch.float).cuda()
        image_tensor = self.generator(noise, label)
        sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
        scipy.misc.imsave('./temporary_images/face4.png', sample[0])


class Gan8(object):
    def __init__(self):
        self.generator = gan_8_15.generator(attribute_count=8).cuda()
        self.generator.load_state_dict(torch.load('./pretrained_weights/gan_8.pth'))
        self.generator.eval()

    def generate(self, embed):
        noise = torch.randn(1, 100).cuda()
        embed = torch.tensor([embed], dtype=torch.float).cuda()
        image_tensor = self.generator(embed, noise)
        sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
        scipy.misc.imsave('./temporary_images/face8.png', sample[0])


class Gan15(object):
    def __init__(self):
        self.generator = gan_8_15.generator(attribute_count=15).cuda()
        self.generator.load_state_dict(torch.load('./pretrained_weights/gan_15.pth'))
        self.generator.eval()

    def generate(self, embed):
        noise = torch.randn(1, 100).cuda()
        embed = torch.tensor([embed], dtype=torch.float).cuda()
        image_tensor = self.generator(embed, noise)
        sample = image_tensor.cpu().data.numpy().transpose(0, 2, 3, 1)
        scipy.misc.imsave('./temporary_images/face15.png', sample[0])


gan2 = Gan2()
gan4 = Gan4()
gan8 = Gan8()
gan15 = Gan15()

app = Flask(__name__)


@app.route('/generate_gan2', methods=['POST'])
def generate_gan2():
    embed_str = request.form.get('embed_str')
    embed = [int(x) for x in embed_str.split(',')]
    gan2.generate(embed)
    return "Done"

@app.route('/generate_gan4', methods=['POST'])
def generate_gan4():
    embed_str = request.form.get('embed_str')
    embed = [int(x) for x in embed_str.split(',')]
    gan4.generate(embed)
    return "Done"

@app.route('/generate_gan8', methods=['POST'])
def generate_gan8():
    embed_str = request.form.get('embed_str')
    embed = [int(x) for x in embed_str.split(',')]
    gan8.generate(embed)
    return "Done"

@app.route('/generate_gan15', methods=['POST'])
def generate_gan15():
    embed_str = request.form.get('embed_str')
    embed = [int(x) for x in embed_str.split(',')]
    gan15.generate(embed)
    return "Done"

if __name__ == '__main__':
    app.run(host='localhost', port='8889')